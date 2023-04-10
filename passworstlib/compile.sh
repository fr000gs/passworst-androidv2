#!/bin/bash
cargo build -j12 --target aarch64-linux-android
cargo build -j12 --target x86_64-linux-android
cargo build -j12 --target armv7-linux-androideabi
cargo build -j12 --target i686-linux-android

cargo build -j12 --release --target aarch64-linux-android
cargo build -j12 --release --target x86_64-linux-android
cargo build -j12 --release --target armv7-linux-androideabi
cargo build -j12 --release --target i686-linux-android

