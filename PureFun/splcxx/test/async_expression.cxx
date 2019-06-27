
#include <thread>
#include <future>
#include <iostream>


int32_t main(int32_t argc, char** argv) {
	std::packaged_task<int32_t(int32_t, int32_t)> func_task([=](int32_t a, int32_t b){ std::this_thread::sleep_for (std::chrono::seconds(5)); return a + b; });
	std::future<int32_t> result_task = func_task.get_future();
	std::thread task(std::move(func_task), 2, 4);
	task.detach();

  std::this_thread::sleep_for (std::chrono::seconds(4));

	std::cout << "do something else ..." << std::endl;

	std::cout << "wait" << std::endl;
	result_task.wait();
	std::cout << result_task.get() << std::endl;

	return 0;
}
