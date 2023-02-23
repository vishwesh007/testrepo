import pyautogui
import time
import keyboard as kb

#


while(True):
    
        time.sleep(20)

        pyautogui.locateCenterOnScreen('run.png')
        pyautogui.moveTo(384, 343)

        time.sleep(5)

        pyautogui.click()

        pyautogui.moveTo(533,693)

        time.sleep(3)
        pyautogui.click()
#pyautogui.scroll(10)
        time.sleep(3)
#pyautogui.press('down')
#pyautogui.scroll(-100)
        pyautogui.moveTo(1453,879)
        pyautogui.click()
        pyautogui.typewrite("vi")
        time.sleep(3)
        pyautogui.press('enter')
        pyautogui.press('enter')
        pyautogui.moveTo(1499,573)
        pyautogui.click()
        time.sleep(3)
        pyautogui.moveTo(1477,646)
        pyautogui.click()
        time.sleep(3)
        pyautogui.moveTo(1441,211)

        pyautogui.click()
        time.sleep(20)
        # pyautogui.moveTo(998,839)
        # time.sleep(3)
        # pyautogui.moveTo(946,205)
        # pyautogui.click()
        # time.sleep(3)
        # pyautogui.moveTo(926,256)
        
        # pyautogui.click()
        # time.sleep(3)
        # pyautogui.moveTo(920, 291)
        
print('bye')



