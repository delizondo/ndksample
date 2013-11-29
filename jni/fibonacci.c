/*
 * fibonacci.c
 *
 *  Created on: Nov 27, 2013
 *      Author: davidelizondo
 */
#include "fibonacci.h"

uint64_t calculateFibonacci(long n) {
	if (n > 1) {
		long n1 = 0, n2 = 1;
		do {
			long tmp = n2;
			n2 += n1;
			n1 = tmp;
		} while (--n > 1);
		return n2;
	}
	return n;
}

