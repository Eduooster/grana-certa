import { useBottomTabBarHeight } from 'expo-router/build/react-navigation/bottom-tabs';
import React from 'react';
import { ScrollView, View, StyleSheet, Text } from 'react-native';
import { SafeAreaView } from 'react-native-safe-area-context';

interface ScreenContainerProps {
  children: React.ReactNode;
  scrollable?: boolean;
  backgroundColor?: string;
  flowBackground?: boolean;
}

export function ScreenContainer({
  children,
  scrollable = true,
  backgroundColor = '#FFFAF7',
  flowBackground = false,
}: ScreenContainerProps) {
  let tabBarHeight = 0;

  try {
    tabBarHeight = useBottomTabBarHeight();
  } catch (error) {
    tabBarHeight = 0;
  }

  const paddingBottom = tabBarHeight > 0 ? tabBarHeight + 24 : 24;
  const paddingHorizontal = 24;
  const paddingTop = 32;

  if (scrollable) {
    return (
      <SafeAreaView
        style={[styles.container, { backgroundColor }]}
        edges={['top', 'left', 'right']}>
        {flowBackground && (
          <View pointerEvents="none" style={StyleSheet.absoluteFill}>
            <Text style={styles.currencyMark}>$</Text>
            <Text style={[styles.currencyMark, styles.currencyMarkLower]}>R$</Text>
            <View style={styles.flowLineOne} />
            <View style={styles.flowLineTwo} />
            <View style={styles.flowLineThree} />
            <View style={styles.flowLineFour} />
          </View>
        )}
        <ScrollView
          showsVerticalScrollIndicator={false}
          contentContainerStyle={{
            paddingBottom,
            paddingHorizontal,
            paddingTop,
            gap: 20,
          }}>
          {children}
        </ScrollView>
      </SafeAreaView>
    );
  }

  return (
    <SafeAreaView style={[styles.container, { backgroundColor }]} edges={['top', 'left', 'right']}>
      <View style={{ flex: 1, paddingBottom, paddingHorizontal }}>{children}</View>
    </SafeAreaView>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
  currencyMark: {
    position: 'absolute',
    top: 170,
    right: 18,
    color: '#8B5CF6',
    opacity: 0.035,
    fontSize: 92,
    fontWeight: '700',
  },
  currencyMarkLower: {
    top: 625,
    right: undefined,
    left: -15,
    fontSize: 68,
  },
  flowLineOne: {
    position: 'absolute',
    top: 330,
    right: -8,
    width: 110,
    height: 1,
    backgroundColor: '#8B5CF6',
    opacity: 0.07,
    transform: [{ rotate: '-24deg' }],
  },
  flowLineTwo: {
    position: 'absolute',
    top: 307,
    right: 90,
    width: 95,
    height: 1,
    backgroundColor: '#8B5CF6',
    opacity: 0.07,
    transform: [{ rotate: '15deg' }],
  },
  flowLineThree: {
    position: 'absolute',
    top: 740,
    left: -8,
    width: 125,
    height: 1,
    backgroundColor: '#8B5CF6',
    opacity: 0.06,
    transform: [{ rotate: '-18deg' }],
  },
  flowLineFour: {
    position: 'absolute',
    top: 718,
    left: 105,
    width: 92,
    height: 1,
    backgroundColor: '#8B5CF6',
    opacity: 0.06,
    transform: [{ rotate: '12deg' }],
  },
});
