#!/bin/bash
set +x
cat meats.txt | sed -n "$(echo $(( $RANDOM % $(cat meats.txt | wc -l) + 1 )))p"