import pyautogui
button7location = pyautogui.locateOnScreen('edit.png')
button7location

editcenter= pyautogui.locateCenterOnScreen('insertrowsformobsy.png')
print(button7location)
print(editcenter)
pyautogui.moveTo(editcenter)