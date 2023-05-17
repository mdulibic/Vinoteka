package com.example.vinoteka.utils

import com.example.vinoteka.model.Maltster
import com.example.vinoteka.model.Sort
import com.example.vinoteka.model.Wine

val maltster = Maltster.DRY

// Create a list of mock Wine objects
val wineList = listOf(
    Wine(
        "1",
        "Red Wine",
        "2020",
        14.5f,
        maltster,
        "Premium",
        "Vineyard A",
        "16°C",
        "Pairs well with grilled meats",
        "This is a red wine description",
        29.99,
        Sort(
            "1",
            "Cabernet Sauvignon",
            "A full-bodied red wine with rich flavors of dark fruits and a hint of oak.",
        ),
    ),
    Wine(
        "2",
        "White Wine",
        "2019",
        12.0f,
        maltster,
        "Superior",
        "Vineyard B",
        "12°C",
        "Perfect for seafood dishes",
        "This is a white wine description",
        24.99,
        Sort(
            "2",
            "Chardonnay",
            "A medium to full-bodied white wine with buttery and creamy flavors.",
        ),
    ),
    Wine(
        "3",
        "Rose Wine",
        "2021",
        11.5f,
        maltster,
        "Excellent",
        "Vineyard C",
        "10°C",
        "Ideal for outdoor gatherings",
        "This is a rose wine description",
        19.99,
        Sort(
            "3",
            "Grenache",
            "A versatile red wine with a range of flavors from fruity to spicy.",
        ),
    ),
    Wine(
        "3",
        "Rose Wine",
        "2021",
        11.5f,
        maltster,
        "Excellent",
        "Vineyard C",
        "10°C",
        "Ideal for outdoor gatherings",
        "This is a rose wine description",
        19.99,
        Sort(
            "3",
            "Grenache",
            "A versatile red wine with a range of flavors from fruity to spicy.",
        ),
    ),
    Wine(
        "3",
        "Rose Wine",
        "2021",
        11.5f,
        maltster,
        "Excellent",
        "Vineyard C",
        "10°C",
        "Ideal for outdoor gatherings",
        "This is a rose wine description",
        19.99,
        Sort(
            "3",
            "Grenache",
            "A versatile red wine with a range of flavors from fruity to spicy.",
        ),
    ),
    Wine(
        "3",
        "Rose Wine",
        "2021",
        11.5f,
        maltster,
        "Excellent",
        "Vineyard C",
        "10°C",
        "Ideal for outdoor gatherings",
        "This is a rose wine description",
        19.99,
        Sort(
            "3",
            "Grenache",
            "A versatile red wine with a range of flavors from fruity to spicy.",
        ),
    ),
    Wine(
        "3",
        "Rose Wine",
        "2021",
        11.5f,
        maltster,
        "Excellent",
        "Vineyard C",
        "10°C",
        "Ideal for outdoor gatherings",
        "This is a rose wine description",
        19.99,
        Sort(
            "3",
            "Grenache",
            "A versatile red wine with a range of flavors from fruity to spicy.",
        ),
    )

)

// You can now use the wineList as mock data for your wine cards or any other purpose
