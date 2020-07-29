#!/bin/zsh

# Nappa library paths
NAPPA_LIBRARY_BASE_PATH="/home/sshann/Documents/thesis/NAPPA/Prefetching-Library/"
NAPPA_LIBRARY_AAR_PATH="${NAPPA_LIBRARY_BASE_PATH}android_prefetching_lib/build/outputs/aar/"
AAR_ORIGINAL_PATH="${NAPPA_LIBRARY_AAR_PATH}android_prefetching_lib-debug.aar"
AAR_NEW_PATH="${NAPPA_LIBRARY_AAR_PATH}nappa-prefetching-library.aar"

# Subjects paths
SUBJECT_PROJECT_BASE_PATH="/home/sshann/Documents/thesis/subjects/"
AAR_SCRIPT_PATH="libs/aars/"
SUBJECTS_BASE_PATH=(
  # Nappa Greedy
  "${SUBJECT_PROJECT_BASE_PATH}instrumented-nappa-greedy/AntennaPod/app/"
  "${SUBJECT_PROJECT_BASE_PATH}instrumented-nappa-greedy/Hillffair/app/"
  "${SUBJECT_PROJECT_BASE_PATH}instrumented-nappa-greedy/materialistic/app/"
  "${SUBJECT_PROJECT_BASE_PATH}instrumented-nappa-greedy/NewsBlur/clients/android/NewsBlur/"
  "${SUBJECT_PROJECT_BASE_PATH}instrumented-nappa-greedy/RedReader/"
  "${SUBJECT_PROJECT_BASE_PATH}instrumented-nappa-greedy/Travel-Mate/Android/app/"
  "${SUBJECT_PROJECT_BASE_PATH}instrumented-nappa-greedy/uob-timetable-android/uob/uob-timetable/"

  # Nappa TFPR
  "${SUBJECT_PROJECT_BASE_PATH}instrumented-nappa-tfpr/AntennaPod/app/"
  "${SUBJECT_PROJECT_BASE_PATH}instrumented-nappa-tfpr/Hillffair/app/"
  "${SUBJECT_PROJECT_BASE_PATH}instrumented-nappa-tfpr/materialistic/app/"
  "${SUBJECT_PROJECT_BASE_PATH}instrumented-nappa-tfpr/NewsBlur/clients/android/NewsBlur/"
  "${SUBJECT_PROJECT_BASE_PATH}instrumented-nappa-tfpr/RedReader/"
  "${SUBJECT_PROJECT_BASE_PATH}instrumented-nappa-tfpr/Travel-Mate/Android/app/"
  "${SUBJECT_PROJECT_BASE_PATH}instrumented-nappa-tfpr/uob-timetable-android/uob/uob-timetable/"
)

# Build the library
cd $NAPPA_LIBRARY_BASE_PATH || exit
echo "Building NAPPA Prefetching Library"
./gradlew build
echo "NAPPA Library finished building"

# Rename the library
echo "Renaming AAR file"
cp -rf "${AAR_ORIGINAL_PATH}" "${AAR_NEW_PATH}"

# Copy to subjects
count=0
echo "Copying AAR file to subjects"
for base_path in "${SUBJECTS_BASE_PATH[@]}"; do
  if [ -d "${base_path}" ]; then
    path_to_copy="${base_path}${AAR_SCRIPT_PATH}"
    mkdir -p "${path_to_copy}"
    cp -rf "${AAR_NEW_PATH}" "${path_to_copy}"
    count=$((count + 1))
  else
    echo "Subject path not found ${base_path}"
  fi
done
echo "Copied ${count} AAR files"
