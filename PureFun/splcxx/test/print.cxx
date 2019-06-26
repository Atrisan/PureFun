#include <iostream>
#include <string>

#include "gtest/gtest.h"
#include "../core/util/print.hxx"

class test_print : public ::testing::Test {
};

TEST_F(test_print, print_newline) {
	print("Test", " ", "Hello", " ", 10, 13);
	print();
}

int32_t main(int32_t argc, char** argv) {
	::testing::InitGoogleTest(&argc, argv);
	return RUN_ALL_TESTS();
}
