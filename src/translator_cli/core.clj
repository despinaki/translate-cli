(ns translator-cli.core
  (:import [com.google.cloud.translate TranslateOptions Translate$TranslateOption] ;ClassName$NestedClassName
           [java.lang.reflect Array]) 
  
  (:gen-class))
;;             JAVA EXAMPLE
;; import com.google.cloud.translate.*;
;; Translate translate = TranslateOptions.getDefaultInstance().getService();       -->getDefaultInstance() is a class method (static), getService() is an object method
;; Translation translation = translate.translate("¡Hola Mundo!");                  -->we dont need to use the Translate and Translation classes in Clojure (no need to declare the type)
;; System.out.printf("Translated Text:\n\t%s\n", translation.getTranslatedText()); 

(defn translate-text
  "Use google cloud translate api to translate a text"
  [lan text]
  (let [translate (-> (TranslateOptions/getDefaultInstance) .getService) ;Get service using GOOGLE_APPLICATION_CREDENTIALS environment var
        detection (.getLanguage (.detect translate text))
        options (Array/newInstance Translate$TranslateOption 2)]
    (do
      (aset options 0 (Translate$TranslateOption/sourceLanguage detection))
      (aset options 1 (Translate$TranslateOption/targetLanguage lan)))
    ;; (println (vec (.getMethods (class translate))))
    (println (.getTranslatedText (.translate translate text options)))
    ;; (println detection)
    ))


(defn -main
  [lan text]
  (if(and (string? lan) (string? text)) (translate-text lan text) (println "Try running: translate 'lan' 'text', where lan is the 2-letter code in string format and text is in string format also.")))
;add another condition that checks if lang is in list
;add exception handling