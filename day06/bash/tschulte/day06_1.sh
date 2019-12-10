#!/usr/bin/env bash

function initRepo {
    rm -rf repo
    git init repo > /dev/null 2>&1
    git -C repo checkout --orphan COM > /dev/null 2>&1
    git -C repo commit --allow-empty -m "COM" > /dev/null 2>&1
    readarray -t lines < $1
    while [[ ${#lines[@]} > 0 ]]
    do
        current_lines=${lines[@]}
        lines=()
        for line in ${current_lines[@]}
        do
            IFS=')' read -ra split <<< "$line"
            a=${split[0]}
            b=${split[1]}
            if git -C repo checkout "$a" > /dev/null 2>&1
            then
                git -C repo checkout -b "$b" > /dev/null 2>&1
                git -C repo commit --allow-empty -m "$b" > /dev/null 2>&1
            else
                lines+=($line)
            fi
        done
    done
}

function count_orbits {
    git -C repo for-each-ref --format='%(refname)' | while read branch
    do
        count=`git -C repo log --oneline "$branch" | wc -l`
        orbits=$(($orbits + $count - 1))
        echo $orbits
    done
}

initRepo test-input.txt
count_orbits

initRepo input.txt
count_orbits
