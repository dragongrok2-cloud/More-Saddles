# More Saddles

**Мод для Minecraft 1.21.1 (NeoForge) + Dragon Survival**  
Добавляет больше сёдел для драконов!

## Описание

Этот мод расширяет возможности **Dragon Survival**, добавляя новые виды сёдел для ваших драконов.

Сейчас в скелете есть три примера предметов:
- `dragon_saddle_basic` — Простое седло дракона
- `dragon_saddle_fancy` — Нарядное седло дракона
- `dragon_saddle_royal` — Королевское седло дракона

## Требования

- **Minecraft** 1.21.1
- **NeoForge** 21.1.x+
- **Dragon Survival** (рекомендуется, зависимость optional)

## Установка для разработчика

1. Клонируй репозиторий
2. Открой в IntelliJ IDEA / VS Code
3. Запусти `./gradlew genIntellijRuns` (или Eclipse)
4. Запусти клиент через run configuration `client`

```bash
./gradlew runClient
```

## Структура проекта

```
More-Saddles/
├── build.gradle
├── gradle.properties
├── settings.gradle
├── src/main/java/com/dragongrok/moresaddles/
│   └── MoreSaddles.java          ← главный класс мода
├── src/main/resources/
│   └── assets/moresaddles/lang/
│       ├── en_us.json
│       └── ru_ru.json
└── src/main/templates/META-INF/
    └── neoforge.mods.toml
```

## Дальнейшие шаги

- [ ] Добавить настоящую интеграцию с Dragon Survival (седла для драконов)
- [ ] Текстуры и модели
- [ ] Рецепты
- [ ] Конфиг

---

Сделано с любовью твоим добрым драконом с седлом 🐉  
Репозиторий: https://github.com/dragongrok2-cloud/More-Saddles
