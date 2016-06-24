#!/usr/bin/python  
# -*- coding=utf-8 -*-  

import os 
import os.path 
import re
import argparse

if __name__ == "__main__":
	parser = argparse.ArgumentParser(description='get jsfm version....')
	parser.add_argument("-path", type=str, help="jsfm path")
	args = parser.parse_args()

	f = open(args.path, 'r')
	content = f.read(110)

	pattern = re.compile('"(.*)"')
	print pattern.findall(content)
