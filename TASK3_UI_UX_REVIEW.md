# Task 3 – UI/UX & Usability Review

## 1) First Impressions & Onboarding
**Is the purpose clear at first launch?**  
Partially. The app shows lottery result cards (6AUS49, EUROJACKPOT) with “Last Draw / Next Draw” and number circles, but a first-time user may not immediately understand whether these are *winning numbers* or *user selections*.

**What’s missing / what I would add:**
- A top header: **“Latest Lottery Results”**
- A short helper text: **“Winning numbers from the last draw.”**
- A timestamp: **“Updated at HH:mm”** (adds trust and clarity)
- An info tooltip explaining: *Last Draw / Next Draw / special numbers*

---

## 2) Visual Polish & Consistency
**What works well**
- Minimal layout, easy to scan
- Clear separation between cards
- Lottery names are readable and consistent

**Improvements**
- **Spacing / padding:** Rightmost number circles feel too close to the card edge and may clip on some devices.  
  Add consistent horizontal padding and ensure the last circle always fits.
- **Hierarchy:** Make the numbers row the primary focal point.  
  Reduce emphasis of labels (“Last Draw / Next Draw”) and increase spacing between sections.
- **Special numbers consistency:** The super/extra numbers should be visually distinguishable across lotteries.  
  Currently, EuroJackpot extra numbers are rendered with the same style as normal numbers, which makes them harder to identify.

---

## 3) Error States & Edge Cases
**Expected behavior when data fails to load (offline / server error):**
- Never show a blank screen or crash
- Show a clear message + recovery action

**Ideal error screen:**
- **Title:** No Internet Connection
- **Message:** We couldn’t load the latest lottery results. Please check your connection and try again.
- **Primary CTA:** Retry
- **Secondary CTA:** Open Settings

---

## Overall UX Summary
Overall, the UI is clean and easy to scan, but it would benefit from stronger hierarchy, improved spacing for the number circles, consistent handling of special numbers, and robust offline/error states.
