cmake_minimum_required(VERSION 3.22.1)

project("bases")

# Specify C++ standard
set(CMAKE_CXX_STANDARD 17)
set(CMAKE_CXX_STANDARD_REQUIRED ON)

add_library(${CMAKE_PROJECT_NAME} SHARED
        bases.cpp) # Make sure this file exists

# Specify include directories if needed
target_include_directories(${CMAKE_PROJECT_NAME} PRIVATE
        ${CMAKE_SOURCE_DIR}/include)

# Link libraries
target_link_libraries(${CMAKE_PROJECT_NAME}
        android
        log)
