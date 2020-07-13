#!/bin/zsh

# This script will build all 7 subject apps for all 5 treatments and store it in
# the directory <treatment>/apk/<app>.apk

# Obtain the absolute path to the project root directory for moving between directories
PROJECT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" >/dev/null 2>&1 && pwd)"

# The identifier name of the apps directories
APPS_NAME=(
    "AntennaPod"
    "Hillffair"
    "materialistic"
    "NewsBlur"
    "RedReader"
    "Travel-Mate"
    "uob-timetable-android"
)

# The relative path to the Android project inside the app project directory
APPS_ANDROID_DIR=(
    ""                         # AntennaPod
    ""                         # Hillffair
    ""                         # materialistic
    "clients/android/NewsBlur" # NewsBlur
    ""                         # RedReader
    "Android"                  # Travel-Mate
    "app"                      # uob-timetable-android
)

# The identifier name of the treatments directory
TREATMENTS_NAME=(
    "baseline"
    # "instrumented-nappa-greedy"
    # "instrumented-nappa-tfpr"
    # "instrumented-paloma"
    # "perfect"
)

for treatment in $TREATMENTS_NAME; do
    for index in {1..$#APPS_NAME}; do
        # Define the absolute path where the Android project to build is located
        APP_NAME=${APPS_NAME[index]}
        APP_RELATIVE_PATH=${APPS_ANDROID_DIR[index]}
        APP_DIR="${PROJECT_DIR}/${treatment}/${APP_NAME}/${APP_RELATIVE_PATH}"
        echo "Moving to directory $APP_DIR"
        cd $APP_DIR

        # Build apps via Gradle
        echo "Building APK for the app ${APP_NAME} with treatment ${treatment}."
        ./gradlew build

        echo "Finished building APK.\n"
    done
done
