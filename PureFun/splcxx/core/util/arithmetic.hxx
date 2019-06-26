
#include <iostream>

template<typename ...Args>
auto add(Args&&... args) {
	return (0 + ... + args);
}
