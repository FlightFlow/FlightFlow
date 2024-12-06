#!/bin/bash

# NVM AND NODE
echo "Info: Installing Node Version Manager..."

curl -o- https://raw.githubusercontent.com/nvm-sh/nvm/v0.40.0/install.sh | bash
nvm install 22

echo "Info: Succesfully installed Node Version Manager."

# PNPM
echo "Info: Installing pnpm..."

curl -fsSL https://get.pnpm.io/install.sh | sh -

echo "Info: Successfully installed pnpm."

# MAVEN
echo "Info: Installing Maven..."

if ! grep -q 'export PATH=$MAVEN_HOME/bin:$PATH' ~/.bashrc; then
  cd /tmp
  wget https://dlcdn.apache.org/maven/maven-3/3.9.9/binaries/apache-maven-3.9.9-bin.tar.gz
  sudo tar -xvzf apache-maven-3.9.9-bin.tar.gz -C /opt
  sudo rm -rf apache-maven-3.9.9-bin.tar.gz

  echo "" >> ~/.bashrc
  echo "export MAVEN_HOME=/opt/apache-maven-3.9.9" >> ~/.bashrc
  echo 'PATH=$MAVEN_HOME/bin:$PATH' >> ~/.bashrc

  source ~/.bashrc

  echo "Info: Successfully installed Maven."
else
  echo "Info: Maven environment variables found in ~./bashrc. Skipping..."
fi
