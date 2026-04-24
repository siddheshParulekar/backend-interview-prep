# LRU Cache (Low Level Design)

## Overview

Least Recently Used (LRU) Cache evicts the least recently accessed item when capacity is exceeded.

## Approach

* HashMap for O(1) lookup
* Doubly Linked List for maintaining order of usage

## Operations

* get(key): Return value and move node to front
* put(key, value): Insert/update and move to front

## Complexity

* Time: O(1) for both get and put
* Space: O(n)

## Design Decisions

* Used DLL for efficient removal and insertion
* HashMap ensures constant time access

## Real-world Use Cases

* Database caching
* API response caching
* Memory management

## Future Improvements

* Thread-safe implementation
* Distributed caching using Redis
