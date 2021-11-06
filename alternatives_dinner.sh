#!/bin/bash
set +x
cat alternatives.txt | sed -n "$(echo $(( $RANDOM % $(cat alternatives.txt | wc -l) + 1 )))p"