1. Compile rust library (`compile.sh`)
2. Copy the generated libraries to their proper places in app/src/main/jniLibs

Rust| Jni folder
---| ---
aarch64-linux-android| armv64-v8a
armv7-linux-androideabi| armeabi-v7a
x86_64-linux-android| x86_64
i686-linux-android| x86

3. Build using gradle
