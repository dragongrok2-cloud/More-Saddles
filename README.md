# More Saddles

**Мод для Minecraft 1.21.1 (NeoForge) + Dragon Survival**  
Добавляет больше сёдел для драконов!

## Описание

Мод добавляет 3 седла:

| ID | Название (RU) | Название (EN) |
|----|---------------|---------------|
| `dragon_saddle_basic` | Простое седло дракона | Basic Dragon Saddle |
| `dragon_saddle_fancy` | Нарядное седло дракона | Fancy Dragon Saddle |
| `dragon_saddle_royal` | Королевское седло дракона | Royal Dragon Saddle |

### Интеграция с Dragon Survival

- **Мягкая (soft) зависимость** — мод загружается и без DS.
- При наличии Dragon Survival:
  - ПКМ по седлу проверяет, находишься ли ты в форме дракона.
  - Если да — пишет сообщение об экипировке.
  - Если нет — просит превратиться в дракона.
- Используется reflection к известным helper-классам DS (`DragonUtils` и др.), чтобы не ломаться при обновлениях DS.

> Важно: в самом Dragon Survival нет классических «сёдел для езды» (ты *сам* дракон).  
> Сёдла More Saddles — это предметы-аддоны с проверкой формы дракона и заделом под будущие скины/эффекты.

## Требования

- Minecraft **1.21.1**
- NeoForge **21.1.x+**
- Dragon Survival (рекомендуется, optional)

## Структура

```
src/main/java/com/dragongrok/moresaddles/
├── MoreSaddles.java                 — главный класс
├── compat/DragonSurvivalCompat.java — soft-интеграция с DS
└── item/DragonSaddleItem.java       — предмет седла

src/main/resources/assets/moresaddles/
├── lang/en_us.json, ru_ru.json
├── models/item/*.json               — модели предметов
└── textures/item/                   — сюда PNG 16×16
```

## Текстуры-заглушки

Модели уже прописаны. Положи PNG 16×16 в:

```
src/main/resources/assets/moresaddles/textures/item/
  dragon_saddle_basic.png
  dragon_saddle_fancy.png
  dragon_saddle_royal.png
```

Пока файлов нет — в игре будет фиолетово-чёрная missing texture (это нормально).

## Запуск

```bash
./gradlew runClient
```

## Дальше можно добавить

- Рецепты крафта
- Реальные визуальные эффекты / скины через API DS (когда появится стабильный)
- Конфиг
- Curios / слот экипировки

---

Сделано с любовью твоим добрым драконом с седлом 🐉  
https://github.com/dragongrok2-cloud/More-Saddles
