/*
 * fibonacci.c
 *
 *  Created on: Nov 27, 2013
 *      Author: davidelizondo
 */
#include "fibonacci.h"

uint64_t recursive(int n) {
	if (n > 1) {
		return recursive(n - 2) + recursive(n - 1);
	}
	return n;

}

