#!/bin/bash

LATEST_TAG=$(curl "https://api.github.com/repos/OpenListTeam/OpenList-Frontend/releases/latest"| jq -r '.tag_name')

curl -L https://github.com/OpenListTeam/OpenList-Frontend/releases/latest/download/openlist-frontend-dist-${LATEST_TAG}.tar.gz -o dist.tar.gz
rm -rf ../public/dist
mkdir -p ../public/dist
tar -zxvf dist.tar.gz -C ../public/dist
rm -rf dist.tar.gz