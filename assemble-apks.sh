#!/bin/zsh

# Build with ./gradlew assembleDebug
PATHS_1=(
    # Travel Mate
  # "/home/sshann/Documents/thesis/subjects/baseline/Travel-Mate/Android"
  "/home/sshann/Documents/thesis/subjects/instrumented-nappa-greedy/Travel-Mate/Android"
  "/home/sshann/Documents/thesis/subjects/instrumented-nappa-tfpr/Travel-Mate/Android"
  "/home/sshann/Documents/thesis/subjects/instrumented-nappa-paloma/Travel-Mate/Android"

  # Materialistic
  # "/home/sshann/Documents/thesis/subjects/baseline/materialistic"
  "/home/sshann/Documents/thesis/subjects/instrumented-nappa-greedy/materialistic"
  "/home/sshann/Documents/thesis/subjects/instrumented-nappa-tfpr/materialistic"
  "/home/sshann/Documents/thesis/subjects/instrumented-paloma/materialistic"

  # Hillffair
  # "/home/sshann/Documents/thesis/subjects/baseline/Hillffair"
  "/home/sshann/Documents/thesis/subjects/instrumented-nappa-greedy/Hillffair"
  "/home/sshann/Documents/thesis/subjects/instrumented-nappa-tfpr/Hillffair"
  "/home/sshann/Documents/thesis/subjects/instrumented-paloma/Hillffair"
)

echo "Building PATHS_1 with assembleDebug" 
count=0
for base_path_1 in "${PATHS_1[@]}"; do
  echo "-------------------------------------------------------------------------"
  echo "- Build for ${base_path_1}"
  echo "-------------------------------------------------------------------------"
  if [ -d "${base_path_1}" ]; then
    cd "${base_path_1}" || exit 1
    ./gradlew assembleDebug
    count=$((count + 1))
  else
    echo "Path not found"
  echo ""
  fi
done
echo "Built ${count} APK files"
echo ""



# BUild with ./gradlew assembleFreeDebug
PATHS_2=(
  # AntennaPod
  # "/home/sshann/Documents/thesis/subjects/baseline/AntennaPod/"
  "/home/sshann/Documents/thesis/subjects/instrumented-nappa-greedy/AntennaPod/"
  "/home/sshann/Documents/thesis/subjects/instrumented-nappa-tfpr/AntennaPod/"
  "/home/sshann/Documents/thesis/subjects/instrumented-paloma/AntennaPod/"
)

echo "Building PATHS_2 with assembleFreeDebug" 
count=0
for base_path_2 in "${PATHS_2[@]}"; do
  echo "-------------------------------------------------------------------------"
  echo "- Build for ${base_path_2}"
  echo "-------------------------------------------------------------------------"
  if [ -d "${base_path_1}" ]; then
    cd "${base_path_2}" || exit 1
    ./gradlew assembleDebug
    count=$((count + 1))
  else
    echo "Path not found"
  echo ""
  fi
done
echo "Built ${count} APK files"
echo ""


# Build with sudo zsh./gradlew assembleDebug
PATHS_3=(
  # RedReader
  # "/home/sshann/Documents/thesis/subjects/baseline/RedReader"
  "/home/sshann/Documents/thesis/subjects/instrumented-nappa-greedy/RedReader"
  "/home/sshann/Documents/thesis/subjects/instrumented-nappa-tfpr/RedReader"
  "/home/sshann/Documents/thesis/subjects/instrumented-paloma/RedReader"

  # NewsBlur
  # "/home/sshann/Documents/thesis/subjects/baseline/NewsBlur/clients/android/NewsBlur"
  "/home/sshann/Documents/thesis/subjects/instrumented-nappa-greedy/NewsBlur/clients/android/NewsBlur"
  "/home/sshann/Documents/thesis/subjects/instrumented-nappa-tfpr/NewsBlur/clients/android/NewsBlur"
  "/home/sshann/Documents/thesis/subjects/instrumented-paloma/NewsBlur/clients/android/NewsBlur"

  # UOB
  # "/home/sshann/Documents/thesis/subjects/baseline/uob-timetable-android/uob"
  "/home/sshann/Documents/thesis/subjects/instrumented-nappa-greedy/uob-timetable-android/uob"
  "/home/sshann/Documents/thesis/subjects/instrumented-nappa-tfpr/uob-timetable-android/uob"
  "/home/sshann/Documents/thesis/subjects/instrumented-paloma/uob-timetable-android/uob"
)

echo "Building PATHS_3 with assembleDebug" 
count=0
for base_path_3 in "${PATHS_3[@]}"; do
  echo "-------------------------------------------------------------------------"
  echo "- Build for ${base_path_3}"
  echo "-------------------------------------------------------------------------"
  if [ -d "${base_path_3}" ]; then
    cd "${base_path_3}" || exit 1
    sudo sh ./gradlew assembleDebug
    count=$((count + 1))
  else
    echo "Path not found"
  echo ""
  fi
done
echo "Built ${count} APK files"
echo ""