use sha2::{Sha512, Digest};
//use base16ct;
use base64::{engine::general_purpose, Engine as _};
// This is the interface to the JVM that we'll call the majority of our
// methods on.
use jni::JNIEnv;

// These objects are what you should use as arguments to your native
// function. They carry extra lifetime information to prevent them escaping
// this context and getting used after being GC'd.
use jni::objects::{JClass, JString};

// This is just a pointer. We'll be returning it from our function. We
// can't return one of the objects with lifetime information because the
// lifetime checker won't let us.
use jni::sys::{jstring, jint};

// This keeps Rust from "mangling" the name and making it unique for this
// crate.
// This is the class that owns our static method. It's not going to be used,
// but still must be present to match the expected signature of a static
// native method.

fn hash512(string: String) -> String {
    let mut sha512 = Sha512::new();
    sha512.update(string);
    let hash = sha512.finalize();
    //base16ct::lower::encode_string(&hash)
    general_purpose::URL_SAFE_NO_PAD.encode(&hash)
}

/*
fn truncate(hash: String) -> String {
    let mut value = String::new();
    let mut j = 0;
    for i in hash.chars() {
        if j % 8 == 0 {value += &i.to_string();}
        j += 1
    }
    value
}
*/

fn process(user: String, pswd: String, len: i32) -> String {
    //truncate(hash512(pswd+&user))+"@A"
    let mut x = hash512(pswd+&user);
    x.truncate(len.try_into().unwrap());
    x
    //pswd+&user+"@A"
}

#[no_mangle]
pub extern "system" fn Java_io_github_fr000gs_passworst_MainActivity_hello<'local>
(mut env: JNIEnv<'local>,
_class: JClass<'local>,
 user_str: JString<'local>,
  pswd_str: JString<'local>,
  len_int: jint
 )-> jstring {
    // First, we have to get the string out of Java. Check out the `strings`
    // module for more info on how this works.
    let user: String = env.get_string(&user_str)
        .expect("Couldn't get java string!").into();
    let pswd: String = env.get_string(&pswd_str)
        .expect("Couldn't get java string!").into();
    //let len: String = env.get_string(&len_int)
    //        .expect("Couldn't get java string!").into();

    // Then we have to create a new Java string to return. Again, more info
    // in the `strings` module.
    let output = env.new_string(process(user, pswd, len_int))
        .expect("Couldn't create java string!");

    // Finally, extract the raw pointer to return.
    output.into_raw()
}
