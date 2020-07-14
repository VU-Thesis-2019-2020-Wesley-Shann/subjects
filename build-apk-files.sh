#!/bin/zsh

# This script will build all 7 subject apps for all 5 treatments and store it in
# the directory <treatment>/apk/<app>.apk

# Obtain the absolute path to the project root directory for moving between directories
PROJECT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" >/dev/null 2>&1 && pwd)"

# Define the name and path to store the output of the buil command
LOG_FILE_NAME="build-full-log_$(date +%m-%d-%Y-%T).log"
LOG_FILE_PATH="${PROJECT_DIR}/build/logs/${LOG_FILE_NAME}"

# The identifier name of the apps directories
APPS_NAME=(
    "AntennaPod"
    # "Hillffair"
    # "materialistic"
    # "NewsBlur"
    # "RedReader"
    # "Travel-Mate"
    # "uob-timetable-android"
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

# Array containing all apps that failed to build
apps_with_error=()

# Run the command `gradlew build` for all apps~treatment combination
for treatment in $TREATMENTS_NAME; do
    for index in {1..$#APPS_NAME}; do
        # Define the absolute path where the Android project to build is located
        app_name=${APPS_NAME[index]}
        app_relative_path=${APPS_ANDROID_DIR[index]}
        app_dir="${PROJECT_DIR}/${treatment}/${app_name}/${app_relative_path}"
        cd $app_dir

        # Build apps via Gradle
        print -P "%F{blue}%B% Building APK for the app ${app_name} with treatment ${treatment}."
        ./gradlew build >>"${LOG_FILE_PATH}" 2>&1

        # Verifies if the build failed
        gradle_result=$?
        if (($gradle_result != 0)); then
            apps_with_error+=("${treatment} - ${app_name}")
        else
            echo "Finished building APK."
        fi
        echo ""
    done
done

# Print the overview of the build
number_of_apps=$((${#APPS_NAME[@]} * ${#TREATMENTS_NAME[@]}))
number_of_apps_built=$((${number_of_apps} - ${#apps_with_error[@]}))
echo "Finished building apps."
echo "Built a total of ${number_of_apps_built} from ${number_of_apps} apps.\n"

# List all apps that failed to build (if any)
if [ -n "${apps_with_error}" ]; then
    print -P "%F{red}%B% Failed to build the following apps:\n"
    for app in $apps_with_error; do
        print -P "%F{red}%B% $app"
    done
fi
