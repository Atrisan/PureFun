
CXX=c++
CXX_FLAGS=-std=c++17

INCLUDE=-I./googletest/googletest/include
LIB=-L ./googletest/build/lib

all: print async container task

print: print.cxx
	$(CXX) $(CXX_FLAGS) $(INCLUDE) $(LIB) print.cxx -o print -lgtest -lpthread

async: async.cxx
	$(CXX) $(CXX_FLAGS) $(INCLUDE) $(LIB) async.cxx -o async -lgtest -lpthread

task: async_expression.cxx
	$(CXX) $(CXX_FLAGS) $(INCLUDE) $(LIB) async_expression.cxx -o async_expression -lgtest -lpthread

container: container.cxx
	$(CXX) $(CXX_FLAGS) $(INCLUDE) $(LIB) container.cxx -o container -lgtest -lpthread

clean:
	rm print async container
