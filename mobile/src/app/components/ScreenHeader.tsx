import { View, Text, Pressable } from "react-native";
import { Ionicons } from "@expo/vector-icons";

import { colors } from "@/constants/colors";

type ScreenHeaderProps = {
  title: string;
  onProfilePress?: () => void;
  onNotificationPress?: () => void;
  notificationCount?: number;
};

export function ScreenHeader({
  title,
  onProfilePress,
  onNotificationPress,
  notificationCount = 0,
}: ScreenHeaderProps) {
  return (
    <View className="mb-6 w-full flex-row items-center justify-between">
     
      <Pressable
        onPress={onProfilePress}
        className="h-11 w-11 items-center justify-center rounded-full bg-violet-100"
        hitSlop={8}
      >
        <Text className="text-base font-bold text-violet-700">
          ED
        </Text>
      </Pressable>

     
      <Text
        className="mx-3 flex-1 text-center text-xl font-bold text-slate-900"
        numberOfLines={1}
      >
        {title}
      </Text>

     
      <Pressable
        onPress={onNotificationPress}
        className="relative h-11 w-11 items-center justify-center rounded-full border border-slate-100 bg-white"
        hitSlop={8}
      >
        <Ionicons
          name="notifications-outline"
          size={22}
          color="#475569"
        />

        {notificationCount > 0 && (
          <View className="absolute right-0 top-0 min-h-4 min-w-4 items-center justify-center rounded-full bg-red-500 px-1">
            <Text className="text-[10px] font-bold text-white">
              {notificationCount > 9 ? "9+" : notificationCount}
            </Text>
          </View>
        )}
      </Pressable>
    </View>
  );
}