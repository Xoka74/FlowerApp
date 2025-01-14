package com.shurdev.auth.utils

val String.bearer
    get() = "Bearer $this"