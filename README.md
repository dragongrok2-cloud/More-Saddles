# More Saddles

**Мод для Minecraft 1.21.1 (NeoForge) + Dragon Survival + Curios**  
Добавляет больше сёдел для драконов!

## Описание

| ID | RU | EN |
|----|----|----|
| `dragon_saddle_basic` | Простое седло дракона | Basic Dragon Saddle |
| `dragon_saddle_fancy` | Нарядное седло дракона | Fancy Dragon Saddle |
| `dragon_saddle_royal` | Королевское седло дракона | Royal Dragon Saddle |

## Рецепты

**Простое седло**
```
 L 
LSL
 I 
```
`L` = кожа, `S` = нить, `I` = железный слиток

**Нарядное седло**
```
GSG
GBG
GSG
```
`B` = простое седло, `G` = золотой слиток, `S` = нить

**Королевское седло**
```
GDG
NFN
GDG
```
`F` = нарядное седло, `D` = алмаз, `G` = золотой блок, `N` = незеритовый лом

## Curios

- Новый слот: **`dragon_saddle`** (Седло дракона)
- Все 3 седла можно надеть в этот слот
- Зависимость **optional** — без Curios предметы всё равно работают

## Dragon Survival

- Soft-зависимость
- ПКМ проверяет форму дракона
- Reflection к helper-классам DS

## Требования

- Minecraft **1.21.1**
- NeoForge **21.1.x+**
- Dragon Survival (optional)
- Curios **9.x** (optional, для слота)

## Структура

```
src/main/java/.../moresaddles/
├── MoreSaddles.java
├── compat/DragonSurvivalCompat.java
├── compat/CuriosCompat.java
└── item/DragonSaddleItem.java

data/
├── moresaddles/recipe/          — рецепты
├── moresaddles/curios/slots/    — слот dragon_saddle
├── moresaddles/curios/entities/ — слот у игрока
└── curios/tags/item/            — какие предметы идут в слот
```

## Запуск

```bash
./gradlew runClient
```

---

Сделано с любовью твоим добрым драконом с седлом 🐉  
https://github.com/dragongrok2-cloud/More-Saddles
