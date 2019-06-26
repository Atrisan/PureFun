#ifndef UTIL_PRINT_HXX
#define UTIL_PRINT_HXX

#include <iostream>

template<typename ...Args>
void print(Args... args) {
	(std::cout << ... << args) << std::endl;
}

#endif //UTIL_PRINT_HXX
