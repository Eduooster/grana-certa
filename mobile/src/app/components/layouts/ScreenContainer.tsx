import { useBottomTabBarHeight } from 'expo-router/build/react-navigation/bottom-tabs';
import React from 'react';
import { ScrollView, View, StyleSheet } from 'react-native';
import { SafeAreaView } from 'react-native-safe-area-context';


interface ScreenContainerProps {
  children: React.ReactNode;
  scrollable?: boolean;
  backgroundColor?: string;
}

export function ScreenContainer({ 
  children, 
  scrollable = true, 
  backgroundColor = '#FFFAF7'
}: ScreenContainerProps) {
  
  let tabBarHeight = 0;
  
  try {
    tabBarHeight = useBottomTabBarHeight();
  } catch (error) {
    tabBarHeight = 0; 
  }

  const paddingBottom = tabBarHeight > 0 ? tabBarHeight + 24 : 24;
  const paddingHorizontal = 24; 
  const paddingTop = 24; 

  if (scrollable) {
    return (
      <SafeAreaView 
        style={[styles.container, { backgroundColor }]} 
        edges={['top', 'left', 'right']}
      >
        <ScrollView
          showsVerticalScrollIndicator={false}
          contentContainerStyle={{ 
            paddingBottom,
            paddingHorizontal, paddingTop, gap:10
          }} 
        >
          {children}
        </ScrollView>
      </SafeAreaView>
    );
  }

  return (
    <SafeAreaView 
      style={[styles.container, { backgroundColor }]} 
      edges={['top', 'left', 'right']}
    >
      <View style={{ flex: 1, paddingBottom, paddingHorizontal }}>
        {children}
      </View>
    </SafeAreaView>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
});