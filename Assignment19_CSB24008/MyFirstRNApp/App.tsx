import React, { useState } from 'react';
import {
  View,
  Text,
  TouchableOpacity,
  StyleSheet,
  StatusBar,
} from 'react-native';

export default function App(): React.JSX.Element {
  
  const [count, setCount] = useState<number>(0);

  const [isDarkMode, setIsDarkMode] = useState<boolean>(false);

  const handleIncrement = (): void => {
    setCount(count + 1);
  };

  const handleDecrement = (): void => {
    if (count > 0) {
      setCount(count - 1);
    }
  };

  const handleReset = (): void => {
    setCount(0);
  };

  const toggleTheme = (): void => {
    setIsDarkMode(!isDarkMode);
  };


  const backgroundColor = isDarkMode ? '#121212' : '#FFFFFF';
  const textColor = isDarkMode ? '#FFFFFF' : '#000000';
  const buttonColor = isDarkMode ? '#333333' : '#4A90E2';

  return (
    <View style={[styles.container, { backgroundColor }]}>
      <StatusBar
        backgroundColor={backgroundColor}
        barStyle={isDarkMode ? 'light-content' : 'dark-content'}
      />

      <Text style={[styles.heading, { color: textColor }]}>
        Digital Counter App
      </Text>

      
      <Text style={[styles.studentInfo, { color: textColor }]}>
        Debankur Saikia | Roll No: CSB24008
      </Text>

      <Text style={[styles.counterText, { color: textColor }]}>
        {count}
      </Text>

      <View style={styles.buttonRow}>
        <TouchableOpacity
          style={[styles.button, { backgroundColor: buttonColor }]}
          onPress={handleIncrement}
        >
          <Text style={styles.buttonText}>Increment</Text>
        </TouchableOpacity>

        <TouchableOpacity
          style={[styles.button, { backgroundColor: buttonColor }]}
          onPress={handleDecrement}
        >
          <Text style={styles.buttonText}>Decrement</Text>
        </TouchableOpacity>
      </View>

      <TouchableOpacity
        style={[styles.button, styles.resetButton]}
        onPress={handleReset}
      >
        <Text style={styles.buttonText}>Reset</Text>
      </TouchableOpacity>

      <TouchableOpacity
        style={[styles.button, styles.themeButton]}
        onPress={toggleTheme}
      >
        <Text style={styles.buttonText}>
          Toggle Theme
        </Text>
      </TouchableOpacity>
    </View>
  );
}


const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    padding: 20,
  },

  heading: {
    fontSize: 28,
    fontWeight: 'bold',
    marginBottom: 10,
  },

  studentInfo: {
    fontSize: 16,
    marginBottom: 30,
  },

  counterText: {
    fontSize: 70,
    fontWeight: 'bold',
    marginBottom: 40,
  },

  buttonRow: {
    flexDirection: 'row',
    marginBottom: 20,
  },

  button: {
    paddingVertical: 14,
    paddingHorizontal: 20,
    borderRadius: 10,
    marginHorizontal: 10,
    marginVertical: 10,
    minWidth: 130,
    alignItems: 'center',
  },

  resetButton: {
    backgroundColor: '#E74C3C',
  },

  themeButton: {
    backgroundColor: '#27AE60',
  },

  buttonText: {
    color: '#FFFFFF',
    fontSize: 18,
    fontWeight: 'bold',
  },
});