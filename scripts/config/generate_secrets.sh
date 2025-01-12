#!/bin/bash

echo "Info: Generating 5 random strings using openssl library."

echo ""
openssl rand -base64 64
echo ""
openssl rand -base64 64
echo ""
openssl rand -base64 64
echo ""
openssl rand -base64 64
echo ""
openssl rand -base64 64
echo ""

echo "Info: Generated 5 random strings using openssl library."
