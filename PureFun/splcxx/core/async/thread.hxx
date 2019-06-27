#ifndef ASYNC_THREAD_HXX
#define ASYNC_THREAD_HXX

#include <thread>

void sleep(int32_t seconds) {
  std::this_thread::sleep_for (std::chrono::seconds(seconds));
}

#endif //ASYNC_THREAD_HXX
