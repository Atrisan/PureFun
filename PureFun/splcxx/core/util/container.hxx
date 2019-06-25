#ifndef UTIL_CONTAINER_HXX
#define UTIL_CONTAINER_HXX

#include <unordered_map>
#include <vector>
#include <algorithm>
#include <type_traits>

template<typename T>
std::vector<T> concat(std::vector<T> const& left, std::vector<T> const& right) {
	if(left.empty()) { return right; }
	if(right.empty()) { return left; }

	std::vector<T> res;
	res.reserve(left.size() + right.size());
	res.insert(res.cend(), left.cbegin(), left.cend());
	res.insert(res.cend(), right.cbegin(), right.cend());

	return res;
}

template<typename K, typename V>
std::unordered_map<K, V> concat(std::unordered_map<K, V> const& left, std::unordered_map<K, V> const& right) {
	if(left.empty()) { return left; }
	if(right.empty()) { return right; }

	std::unordered_map<K, V> res;
	res.reserve(left.size() + right.size());
	res.insert(right.cbegin(), right.cend());
	res.insert(left.cbegin(), left.cend());

	return res;
}

template<typename T, typename Container>
bool check_in(T const& left, Container const& right) {
	auto it = std::find(right.begin(), right.end(), left);
	if(it == right.end()) { return false; }
	return true;
}

template<typename K, typename V>
std::vector<V> map_values(std::unordered_map<K, V> right) {
	std::vector<V> res;

	for(auto i : right) {
		res.push_back(i.second);
	}

	return res;
}

template<typename K, typename V>
std::vector<K> map_keys(std::unordered_map<K, V> right) {
	std::vector<K> res;

	for(auto i : right) {
		res.push_back(i.first);
	}

	return res;
}

#endif //UTIL_CONTAINER_HXX
