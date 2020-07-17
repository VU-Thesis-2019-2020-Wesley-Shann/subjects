#!/bin/zsh

find $PWD -name "*.apk" | sed -e 's/^/"/g' -e 's/$/",/g' >"list-apk-paths.txt"

echo "APKS are located at"
cat "list-apk-paths.txt"