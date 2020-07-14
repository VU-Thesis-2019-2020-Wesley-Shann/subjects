#!/bin/zsh

# This script will copy the built apks to the directory /build/apks/treatment

start_script=$(date +%s)

# Obtain the absolute path to the project root directory for moving between directories
PROJECT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" >/dev/null 2>&1 && pwd)"

# The identifier name of the apps directories
APPS_NAME=(
    "AntennaPod" # this script fails to create for AntennaPod, use Android Studio instead
    "Hillffair"
    "materialistic"
    "NewsBlur"
    "RedReader"
    "Travel-Mate"
    "uob-timetable-android" # Script returns with
)

APPS_BUILD_DIR=(
    "app/build/outputs/apk/free/debug"                 # AntennaPod
    "app/build/outputs/apk/debug"                      # Hillffair
    "app/build/outputs/apk/debug"                      # materialistic
    "clients/android/NewsBlur/build/outputs/apk/debug" # NewsBlur
    "/build/outputs/apk/debug"                         # RedReader
    "Android/app/build/outputs/apk/debug"              # Travel-Mate
    "app/uob-timetable/build/outputs/apk/debug"        # uob-timetable-android
)

APPS_APK_NAME=(
    "app-free-debug.apk"      # AntennaPod
    "app-debug.apk"           # Hillffair
    "app-debug.apk"           # materialistic
    "NewsBlur-debug.apk"      # NewsBlur
    "RedReader-debug.apk"     # RedReader
    "app-debug.apk"           # Travel-Mate
    "uob-timetable-debug.apk" # uob-timetable-android
)

# The identifier name of the treatments directory
TREATMENTS_NAME=(
    "baseline"
    "instrumented-nappa-greedy"
    "instrumented-nappa-tfpr"
    "instrumented-paloma"
    "perfect"
)

apk_dir="${PROJECT_DIR}/build/apks"
cd $apk_dir
find . -name "*.apk" -delete

# Run the command `gradlew build` for all apps~treatment combination
for treatment in $TREATMENTS_NAME; do
    for index in {1..$#APPS_NAME}; do
        # Move generated APK to final location
        app_name=${APPS_NAME[index]}
        app_build_relative_path=${APPS_BUILD_DIR[index]}
        app_apk_name=${APPS_APK_NAME[index]}
        app_apk_path="${PROJECT_DIR}/${treatment}/${app_name}/${app_build_relative_path}/${app_apk_name}"

        if [ -f "${app_apk_path}" ]; then
            cp "${app_apk_path}" "${apk_dir}/${treatment}/${treatment}-${app_name}.apk"
        else
            echo echo "not found ${app_apk_path}"
        fi
    done
done

# Write to file the absoule path to all apps
cd $apk_dir
find "$(pwd)" -name "*.apk" >"list-apk-paths.txt"

# Print the execution duration of this script
end_script=$(date +%s)
runtime_script=$((end_script - start_script))
echo "\nBuild finished in ${runtime_script} seconds."
