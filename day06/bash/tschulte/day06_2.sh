#!/usr/bin/env bash


function count_transfers {
    echo "$((`git -C repo log --oneline YOU..SAN~ | wc -l` + `git -C repo log --oneline SAN..YOU~ | wc -l`))"
}

count_transfers
