#include <iostream>
#include <string>
#include <thread>
#include <vector>

#include "gtest/gtest.h"
#include "../core/async/safe_queue.hxx"

class test_safe_queue : public ::testing::Test {
protected:
	test_safe_queue() {
		for (int32_t i = 0; i < NUM_PRODUCER; i++) {
			std::cerr << "start producer " << i << std::endl;
			thread_pool_.push_back(std::thread(&test_safe_queue::producer, this));
		}
	}

	~test_safe_queue(){}

	void WaitAll() {
		for(auto& i : thread_pool_) {
			if (i.joinable()) {
				i.join();
			}
		}
	}

	void StartProducer() {
		std::cerr << "start producer " << std::endl;
		thread_pool_.push_back(std::thread(&test_safe_queue::producer, this));
		NUM_PRODUCER++;
	}

	void StartConsumer() {
		std::cerr << "start consumer" << std::endl;
		thread_pool_.push_back(std::thread(&test_safe_queue::consumer, this));
	}

	size_t NUM_PRODUCER = 3;
	const size_t NUM_ELEMENTS = 900000;

	safe_queue<int32_t> q_;
	std::vector<std::thread> thread_pool_;

private:
	void producer(void) {
		for(int32_t i = 0; i < NUM_ELEMENTS; i++) {
			q_.push(i);
		}
	}

	void consumer(void) {
		while(!q_.empty()) {
			int32_t item;
			q_.pop(item);
		}
	}

};

TEST_F(test_safe_queue, queue_elements_add_producer) {
	StartConsumer();
	StartProducer();
	StartProducer();
	StartConsumer();
	WaitAll();
	ASSERT_TRUE(q_.empty());
}

TEST_F(test_safe_queue, queue_elements_check_queue_elements) {
	WaitAll();
	ASSERT_EQ(NUM_PRODUCER * NUM_ELEMENTS, q_.size());
}

TEST_F(test_safe_queue, queue_elements_multiple_consumer_and_producer_empty) {
	WaitAll();
	ASSERT_EQ(NUM_PRODUCER * NUM_ELEMENTS, q_.size());
	StartConsumer();
	StartConsumer();
	WaitAll();
	ASSERT_TRUE(q_.empty());
}

TEST_F(test_safe_queue, queue_elements_consumer_and_producer_empty) {
	WaitAll();
	ASSERT_EQ(NUM_PRODUCER * NUM_ELEMENTS, q_.size());
	StartConsumer();
	WaitAll();
	ASSERT_TRUE(q_.empty());
}

TEST_F(test_safe_queue, queue_is_empty) {
	StartConsumer();
	WaitAll();
	ASSERT_TRUE(q_.empty());
}

int32_t main(int32_t argc, char** argv) {
	::testing::InitGoogleTest(&argc, argv);
	return RUN_ALL_TESTS();
}
