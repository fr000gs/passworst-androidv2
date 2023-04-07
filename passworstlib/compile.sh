#!/bin/bash
# Run while in passworstlib directory

cargo build -j12 --target aarch64-linux-android
cargo build -j12 --target x86_64-linux-android
cargo build -j12 --target armv7-linux-androideabi
cargo build -j12 --target i686-linux-android

cargo build -j12 --release --target aarch64-linux-android
cargo build -j12 --release --target x86_64-linux-android
cargo build -j12 --release --target armv7-linux-androideabi
cargo build -j12 --release --target i686-linux-android

cp target/aarch64-linux-android/release/libpassworstlib.so ../app/src/main/jniLibs/arm64-v8a/
cp target/armv7-linux-androideabi/release/libpassworstlib.so ../app/src/main/jniLibs/armeabi-v8a/
cp target/x86_64-linux-android/release/libpassworstlib.so ../app/src/main/jniLibs/x86_64/
cp target/i686-linux-android/release/libpassworstlib.so ../app/src/main/jniLibs/x86/
