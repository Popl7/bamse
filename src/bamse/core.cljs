(ns bamse.core
  (:require [re-frame.core :as re-frame]
            [bamse.config :as config]
            [bamse.events :as events]
            [bamse.redux  :as redux]
            [bamse.subs   :as subs]
            [bamse.views  :as views]))

(defn dev-setup []
  (when config/debug?
    (println "[App] running in development mode")
    (when config/client?
      (enable-console-print!))
    (when config/redux?
      (redux/setup))))

(defn init [& [state]]
  (dev-setup)
  (events/register)
  (subs/register)
  (re-frame/clear-subscription-cache!)
  (if state
    (re-frame/dispatch-sync [::events/set-db state])
    (re-frame/dispatch-sync [::events/initialize-db])))

(defn app-view []
  [views/page])
