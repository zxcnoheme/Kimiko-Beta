<div align="center">

<img src="https://img.shields.io/badge/Minecraft-1.21.11-2a9d8f" alt="Minecraft"/>
<img src="https://img.shields.io/badge/Fabric-Loader&#8212;0.19%2B-dbd0b4" alt="Fabric Loader"/>
<img src="https://img.shields.io/badge/Version-v1.5-e76f51" alt="Version"/>
<img src="https://img.shields.io/badge/Client-Side-26a69a" alt="Client-Side"/>

<h1 align="center">🎌 Kimiko</h1>

> L0gHem base Kimiko visuals<br/>
> **FIX BY : @NoHemeStudio**

**Хитбоксы, HUD, визуальные эффекты, управление реальной музыкой и уйма утилит — всё в одном клиентском моде для Minecraft 1.21.11.**

</div>

---

## 📸 Скриншоты

```
Добавь сюда скриншоты (главное меню, HUD, ClickGui, MusicBar)
```

## ✨ О моде

Kimiko — это мощный клиентский Fabric-мод, собранный на базе визуальных наработок **L0gHem**. Он добавляет десятки модулей для кастомизации HUD, улучшения визуала, удобных игровых утилит и работы с реально проигрываемой музыкой за пределами игры — прямо поверх вашего интерфейса.

Мод не трогает серверную логику — это чистый **client-side** инструмент.

---

## 🧩 Возможности

### 🎨 Интерфейс и HUD
- **MusicBar** — панель реально играемого трека: обложка слева, название справа, снизу кнопки `назад / пауза / вперёд`
- Watermark, ArrayList, Armor, Inventory, TargetHUD, Potions, Cooldowns, HP Focus
- HotKeys, KeyStrokes, Scoreboard, BossBar, Info, Notifications, Reflection
- Перетаскивание и настройка каждого элемента HUD в отдельном drag-режиме

### 🌄 Визуальные модули
- Ambience, FogBlur, GlassVapor, HitBubbles, Hitboxes, HitColor, HitParticles
- KillEffect, ModelCollapse, NameTags, GlowESP, SeeInvisible, ChinaHat, Emotions
- Trails, WorldParticles, Crosshair, ItemHighlight, DeathCoords, WastedDeath
- ViewModel, ItemPhysics, ItemScroller, ShaderHands, LootView, PortalLive, ShulkerPreview

### ⚔️ Игровые утилиты
- AutoSprint, BetterMinecraft, AspectRatio, FastExp, AutoSwap, AutoCommands
- Electric ClickPearl, AutoDuel, AutoResell, AutoTpAccept, ElytraSwap, TapeMouse
- CrystalOptimizer, Optimization, FreeLook, HandSwap, FakePlayer, SelfPredictions
- ProjectileHelper, TargetESP, BrewViewer, StreamerMode, TalTracker, HolyWorldHelper

### 🎵 Медиа
- Встроенный мост с системным медиаплеером (текущий трек, обложка, управление)
- **LyricsText** — таймингованные субтитры песни в мире
- Голосовые бинды, пати-голос, голосовые заметки и радио

### 🔒 Конфиденциальность
- Сбор телеметрии Mojang отключён (спуф железа и CPU)
- `machineId` подменяется случайным значением
- Сетевая политика выключена по умолчанию и никогда не отправляет данные, пока вы сами не разрешите конкретный домен

### 🧰 И многое другое
- ClickGui, CustomHotbar, CustomSwords, Card-система, Party, Globals
- God Eye, Shulker/Items, HandSwitch и ещё с десяток приятных мелочей

---

## 🎹 MusicBar

Пока играете — Кимо следит за тем, что играет в системном плеере:

```
🗞️ [обложка]  Название трека
       ─────────────────
         ◀︎      ❚❚/▶      ▶
        [назад] [пауза]  [вперёд]
```

- **Обложка** трека — слева в углу
- **Название** — справа от обложки (обрезается, если длинное)
- **Кнопки управления** — снизу: предыдущий трек, пауза/воспроизведение, следующий
- Панель появляется автоматически, когда в системном плеере есть активный трек, и остаётся доступной даже на паузе
- Позицию можно свободно перетащить в режиме настройки HUD

---

## 📦 Установка

1. Скачай сборку **Kimiko v1.5** и положи `kimiko-1.5.jar` в папку `mods`
2. Убедись, что установлен **Fabric Loader 0.19.x** для **Minecraft 1.21.11**
3. Также понадобятся:
   - `Fabric API` для 1.21.11
   - `Fabric Language Kotlin`

```
mods/
├── kimiko-1.5.jar
├── fabric-api*.jar
└── fabric-language-kotlin*.jar
```

4. Запусти игру через Fabric-профиль — готово!

---

## 🛠️ Сборка из исходников

Требуется **JDK 21+**.

```bash
# Windows
.\gradlew.bat build -x test

# Linux / macOS
./gradlew build -x test
```

Готовый мод появится в `build/libs/kimiko-1.5.jar`.

> Примечание: для быстрой пересборки с нагревом кэша можно добавить флаг `--offline`.

---

## ⚙️ Быстрый старт

- Открой **ClickGui** и включай модули — большинство настраивается прямо из окна
- Каждый HUD-элемент можно двигать мышью в **drag-режиме** и менять его параметры
- Стандартные настройки хранятся в папке конфига мода (`config/kimiko/`)

---

## 🧾 Кредиты

- **L0gHem** — оригинальная база визуалов
- **@NoHemeStudio** — фиксы, причёсывание, фиче-правки

---

<div align="center">

**Если мод тебе полезен — поставь ⭐ и поделись с друзьями!**

</div>