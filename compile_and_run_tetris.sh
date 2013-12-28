#!/bin/bash

BASEDIR=$(dirname $0)

if [ ! -d $BASEDIR/bin ]
then
    mkdir $BASEDIR/bin
fi

javac @options @classes

java -classpath bin/ tetris.Tetris