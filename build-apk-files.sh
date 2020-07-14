#!/bin/zsh

# This script will build all 7 subject apps for all 5 treatments and store it in
# the directory build/apk/<app>.apk. The apps are built using the command
# `gradlew assembleDebug` or a custom command used by the app.
# This script expects that the apps are organized in the following structure:
# - root directory
#   - build-apk-files.sh
#   - treatement1
#       - app1
#       - app2
#   - treatement2
#       - app1
#       - app2
# For N treatments and M apps. The apps and treatements names must be edited directly
# in this script in the properties `APPS_NAME`, `APPS_ANDROID_DIR` and `TREATMENTS_NAME`.

start_script=$(date +%s)

# Obtain the absolute path to the project root directory for moving between directories
PROJECT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" >/dev/null 2>&1 && pwd)"

# Define the name and path to store the output of the buil command
TIMESTAMP="$(date +%m-%d-%Y-%T)"
LOG_FILE_NAME="build_${TIMESTAMP}_full.log"
LOG_FILE_PATH="${PROJECT_DIR}/build/logs/${LOG_FILE_NAME}"
OVERVIEW_LOG_FILE_NAME="build_${TIMESTAMP}_overview.log"
OVERVIEW_LOG_FILE_PATH="${PROJECT_DIR}/build/logs/${OVERVIEW_LOG_FILE_NAME}"

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

# Indexes for apps that requires custom assemble command
IDX_ANTENNA_POD=1

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

APPS_BUILD_DIR=(
    "app/build/outputs/apk/free"                       # AntennaPod
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
    # "instrumented-nappa-greedy"
    # "instrumented-nappa-tfpr"
    # "instrumented-paloma"
    # "perfect"
)

# Overview array with the apps that failed/succed to build
builds_with_error=()
passing_builds=()

# Run the command `gradlew build` for all apps~treatment combination
for treatment in $TREATMENTS_NAME; do
    for index in {1..$#APPS_NAME}; do
        start_app=$(date +%s)

        # Define the absolute path where the Android project to build is located
        app_name=${APPS_NAME[index]}
        app_relative_path=${APPS_ANDROID_DIR[index]}
        app_dir="${PROJECT_DIR}/${treatment}/${app_name}/${app_relative_path}"
        cd $app_dir

        treatment_app="${treatment} - ${app_name}"

        # Build apps via Gradle
        print -P "%F{blue}%B% Building ${treatment_app}."
        echo "Building ${treatment_app}.\n" >>"${LOG_FILE_PATH}" 2>&1
        ./gradlew assembleDebug >>"${LOG_FILE_PATH}" 2>&1

        # Verifies if the build failed
        gradle_result=$?
        if (($gradle_result != 0)); then
            builds_with_error+=("${treatment_app}")
            print -P "%F{red}%B% Build failed with code ${gradle_result}."
        else
            passing_builds+=("${treatment_app}")
            print -P "%F{green}%B% Build completed."
        fi

        # Move generated APK to final location
        app_build_relative_path=${APPS_BUILD_DIR[index]}
        app_apk_name=${APPS_APK_NAME[index]}
        app_apk_path="${PROJECT_DIR}/${treatment}/${app_name}/${app_build_relative_path}/${app_apk_name}"
        apk_dir="${PROJECT_DIR}/build/apks"

        echo "APK should be at ${app_apk_path}"
        if [ -f "${app_apk_path}" ]; then
            echo "Found ${app_apk_path}"
            cp "${app_apk_path}" "${apk_dir}/${treatment}-${app_name}.apk"
        else
            echo "not found ${app_apk_path}"
        fi

        # Print app buld duration
        end_app=$(date +%s)
        runtime_app=$((end_app - start_app))
        print -P "%F{white}Build finished in ${runtime_app} seconds.\n"
    done
done

# Print the overview of the build
number_of_apps=$((${#APPS_NAME[@]} * ${#TREATMENTS_NAME[@]}))
print -P "%F{white}Finished building apps."
echo "Attempted to build ${number_of_apps} apps."
print -P "%F{green}%B% ${#passing_builds[@]} apps were successfully built."
print -P "%F{red}%B% ${#builds_with_error[@]} apps failed to built."
echo ""

# List all apps that were built (if any)
if [ -n "${passing_builds}" ]; then
    print -P "%F{green}%B% Built apps:\n"
    for app in $passing_builds; do
        print -P "* %F{green}%B% $app"
    done
fi

echo ""

# List all apps that failed to build (if any)
if [ -n "${builds_with_error}" ]; then
    print -P "%F{red}%B% Failed to build the following apps:\n"
    for app in $builds_with_error; do
        print -P "* %F{red}%B% $app"
    done
fi

# Print location of file logs
echo ""
print -P "%F{white}See the log file at ${LOG_FILE_PATH} for the complete output of gradlew build."
print -P "%F{white}See the log file at ${OVERVIEW_LOG_FILE_PATH} for the this overview."

# Print the execution duration of this script
end_script=$(date +%s)
runtime_script=$((end_script - start_script))
echo "\nBuild finished in ${runtime_script} seconds."

# Log the overview printed in the console into a file
echo "\nLogging results..."
cat <<EOT >>"${OVERVIEW_LOG_FILE_PATH}"
Runtime: ${runtime_script} seconds.

Attempted to build ${number_of_apps} apps.
${#passing_builds[@]} apps were successfully built.
${#builds_with_error[@]} apps failed to built.

Apps builded:
EOT
if [ -n "${passing_builds}" ]; then
    for app in $passing_builds; do
        echo "* $app" >>"${OVERVIEW_LOG_FILE_PATH}"
    done
else
    echo "No apps built =/" >>"${OVERVIEW_LOG_FILE_PATH}"
fi

echo "\nFailed to build the following apps:" >>"${OVERVIEW_LOG_FILE_PATH}"
if [ -n "${builds_with_error}" ]; then
    for app in $builds_with_error; do
        echo "* $app" >>"${OVERVIEW_LOG_FILE_PATH}"
    done
else
    echo "No app failed to build =D" >>"${OVERVIEW_LOG_FILE_PATH}"
fi
