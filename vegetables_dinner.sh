#!/bin/bash
set +x
cat vegetables.txt | sed -n "$(echo $(( $RANDOM % $(cat vegetables.txt | wc -l) + 1 )))p"