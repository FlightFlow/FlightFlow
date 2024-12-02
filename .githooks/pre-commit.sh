#!/bin/bash

echo "[PRE-COMMIT] Info: Starting linting process..."

cd ../../server
flake8 . && pylint app && bandit -r app

cd ../frontend
pnpm run lint && prettier --write

echo "[PRE-COMMIT] Info: Completed linting process."

# echo "[PRE-COMMIT] Info: Starting test checks..."

# has_test() {
#   local file="$1"
#   local test_file

#   case "$file" in
#     *py)
#       test_file="${file%.py}.test.py"
#       ;;
#     *js | *ts)
#       test_file="${file%.js}.test.js"
#       if [[ ! -f $test_file ]]; then
#         test_file="${file%.ts}.test.ts"
#       fi
#       ;;
#     *)
#       return 0
#       ;;
#   esac
#   if [[ -f $test_file ]]; then
#     return 0 # have tests
#   else
#     return 1 # does not have tests
#   fi
# }

# staged_files=$(git diff --cached --name-only --diff-filter=ACM)

# for file in $staged_files; do
#   if [[ $file == *.py || $file = *.js || $file == *.ts ]]; then
#     if ! has_test "$file"; then
#       echo "[PRE-COMMIT] Error: No test file found for $file."
#       exit 1
#     fi
#   fi
# done

# echo "[PRE-COMMIT] Info: Finished test checks."

exit 0
