#!/bin/sh 

git status . | grep deleted | sed -e "s/.*deleted:/git rm /g" > a.sh;sh a.sh;rm a.sh
