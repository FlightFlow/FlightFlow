#!/bin/bash

MAVEN_VERSION="apache-maven-3.9.9"

NVM_SOURCE="https://raw.githubusercontent.com/nvm-sh/nvm/v0.40.0/install.sh"
PNPM_SOURCE="https://get.pnpm.io/install.sh"
MAVEN_SOURCE="https://dlcdn.apache.org/maven/maven-3/3.9.9/binaries/$MAVEN_VERSION.tar.gz"

# NVM AND NODE
echo "Info: Installing Node Version Manager..."

curl -o- "$NVM_SOURCE" | bash
nvm install 22

echo "Info: Succesfully installed Node Version Manager."

# PNPM
echo "Info: Installing pnpm..."

curl -fsSL "$PNPM_SOURCE"| sh -

echo "Info: Successfully installed pnpm."

# MAVEN
echo "Info: Installing Maven..."

if ! grep -q 'export PATH=$MAVEN_HOME/bin:$PATH' ~/.bashrc; then
  cd /tmp
  wget "$MAVEN_SOURCE"
  sudo tar -xvzf "$MAVEN_VERSION-bin.tar.gz" -C /opt
  sudo rm -rf "$MAVEN_VERSION-bin.tar.gz"

  echo "" >> ~/.bashrc
  echo "export MAVEN_HOME=/opt/$MAVEN_VERSION" >> ~/.bashrc
  echo 'PATH=$MAVEN_HOME/bin:$PATH' >> ~/.bashrc

  source ~/.bashrc

  echo "Info: Successfully installed Maven."
else
  echo "Info: Maven environment variables found in ~./bashrc. Skipping..."
fi
