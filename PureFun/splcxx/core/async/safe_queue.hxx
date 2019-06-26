#ifndef ASYNC_SAFE_QUEUE
#define ASYNC_SAFE_QUEUE

#include <thread>
#include <mutex>
#include <atomic>
#include <condition_variable>
#include <queue>

template<typename T>
class safe_queue {
public:
	safe_queue();
	~safe_queue();

public:
	void push(T&& item);
	void push(T const& item);

	void pop(T& item);
	bool empty();
	size_t size();

	void signal();

private:
	std::queue<T> queue_;
	
private:
	std::mutex mx_;
	std::atomic<bool> signal_;
	std::condition_variable cv_;
};

template<typename T>
safe_queue<T>::safe_queue() : signal_(true) {}

template<typename T>
safe_queue<T>::~safe_queue() {}

template<typename T>
void safe_queue<T>::push(T&& item) {
	{ std::lock_guard<std::mutex> lock(mx_);
		queue_.push(std::forward(item)); }
	cv_.notify_one();
}

template<typename T>
void safe_queue<T>::push(T const& item) {
	{ std::lock_guard<std::mutex> lock(mx_);
		queue_.push(item); }
	cv_.notify_one();
}

template<typename T>
void safe_queue<T>::pop(T& item) {
	std::unique_lock<std::mutex> lock(mx_);
	cv_.wait(lock, [this] {return signal_ || !queue_.empty();});

	if(!queue_.empty()) {
		item = std::move(queue_.front());
		queue_.pop();
	}
}

template<typename T>
void safe_queue<T>::signal() {
	signal_ = true;
	cv_.notify_all();
}

template<typename T>
bool safe_queue<T>::empty() {
	std::lock_guard<std::mutex> lock(mx_);
	return queue_.empty();
}

template<typename T>
size_t safe_queue<T>::size() {
	std::lock_guard<std::mutex> lock(mx_);
	return queue_.size();
}

#endif //ASYNC_SAFE_QUEUE
