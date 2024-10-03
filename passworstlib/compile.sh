#!/bin/bash
#rustup target add aarch64-linux-android armv7-linux-androideabi x86_64-linux-android i686-linux-android

#cargo build -j12 --target aarch64-linux-android
#cargo build -j12 --target x86_64-linux-android
#cargo build -j12 --target armv7-linux-androideabi
#cargo build -j12 --target i686-linux-android#

#cargo build -j12 --release --target aarch64-linux-android
#cargo build -j12 --release --target x86_64-linux-android
#cargo build -j12 --release --target armv7-linux-androideabi
#cargo build -j12 --release --target i686-linux-android

cargo ndk -t armeabi-v7a -t arm64-v8a -t x86 -t x86_64 -o ./jniLibs build --release

#cp target/aarch64-linux-android/release/passworstlib.so app/src/main/jniLibs/arm64-v8a
#cp target/armv7-linux-androideabi/release/passworstlib.so app/src/main/jniLibs/armeabi-v7a
#cp target/x86_64-linux-android/release/passworstlib.so app/src/main/jniLibs/x86_64
#cp target/i686-linux-android/release/passworstlib.so app/src/main/jniLibs/x86