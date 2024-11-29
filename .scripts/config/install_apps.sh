#!/bin/bash

# NVM AND NODE
echo "Info: Installing Node Version Manager"

curl -o- https://raw.githubusercontent.com/nvm-sh/nvm/v0.40.0/install.sh | bash
nvm install 22

echo "Info: Succesfully installed Node Version Manager"

# PNPM
echo "Info: Installing pnpm..."

curl -fsSL https://get.pnpm.io/install.sh | sh -

echo "Info: Successfully installed pnpm."
