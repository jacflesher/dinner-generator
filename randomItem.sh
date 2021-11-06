#!/bin/bash
cat $1.txt | sed -n "$(echo $(( $RANDOM % $(cat $1.txt | wc -l) + 1 )))p"
