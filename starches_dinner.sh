#!/bin/bash
set +x
cat starches.txt | sed -n "$(echo $(( $RANDOM % $(cat starches.txt | wc -l) + 1 )))p"