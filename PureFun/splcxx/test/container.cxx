#include <iostream>
#include <string>
#include <unordered_map>
#include <vector>

#include "gtest/gtest.h"
#include "../core/util/container.hxx"

class test_container : public ::testing::Test {
	protected:
		void SetUp() override {
			vec_.insert(vec_.end(), {0, 1, 2, 3, 4, 5, 6, 7, 8, 9});
			map_.insert({{0, 'a'},{1, 'b'},{2, 'c'},{3, 'd'},{4, 'e'},{5, 'f'},{6, 'g'},{7, 'h'}});
		}

		std::vector<int32_t> vec_;
		std::unordered_map<int32_t, char> map_;
};

TEST_F(test_container, check_is_in_vec_success) {
	for (auto i : vec_) {
		ASSERT_TRUE(check_in(i, vec_));
	}
}

TEST_F(test_container, check_is_in_vec_failure) {
	for (auto i : vec_) {
		ASSERT_FALSE(check_in(i + 10, vec_));
	}
}

TEST_F(test_container, check_is_in_map_success) {
	for (auto i : map_) {
		ASSERT_TRUE(check_in(i.first, vec_));
	}
}

TEST_F(test_container, check_is_in_map_failure) {
	for (auto i : map_) {
		ASSERT_FALSE(check_in(i.first + 10, vec_));
	}
}


TEST_F(test_container, concat_vec) {
	auto res = concat(vec_, {10, 11, 12});
	for(int32_t i = 0; i < 13; i++) {
		ASSERT_EQ(res[i], i);
	}
}

TEST_F(test_container, concat_map_disjoint) {
	auto res = concat(map_, {{8, 'i'}, {9, 'i'}, {10, 'j'}, {11, 'k'}});
	std::unordered_map<int32_t, char> gold {
		{0, 'a'},{1, 'b'},{2, 'c'},{3, 'd'},{4, 'e'},{5, 'f'},{6, 'g'},{7, 'h'}, {8, 'i'}, {9, 'i'}, {10, 'j'}, {11, 'k'}};

	for(auto i : gold) {
		ASSERT_EQ(i.second, res[i.first]);
	}
}

TEST_F(test_container, concat_map_same_keys) {
	auto res = concat(map_, {{5, 'i'}, {6, 'i'}, {7, 'j'}, {8, 'k'}});
	std::unordered_map<int32_t, char> gold {
		{0, 'a'},{1, 'b'},{2, 'c'},{3, 'd'},{4, 'e'},{5, 'i'},{6, 'i'},{7, 'j'}, {8, 'k'}};

	for(auto i : gold) {
		ASSERT_EQ(i.second, res[i.first]);
	}
}


TEST_F(test_container, map_value) {
	auto res = map_values(map_);
	for(int32_t i = 0; i < 8; i++) {
		ASSERT_TRUE(check_in(map_[i], res));
	}
}

TEST_F(test_container, map_keys) {
	auto res = map_keys(map_);
	for(int32_t i = 0; i < 8; i++) {
		ASSERT_TRUE(check_in(i, res));
	}
}

int32_t main(int32_t argc, char** argv) {
	::testing::InitGoogleTest(&argc, argv);
	return RUN_ALL_TESTS();
}
